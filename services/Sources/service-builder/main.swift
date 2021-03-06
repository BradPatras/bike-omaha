import Foundation
import ArgumentParser

private class Keys {
    static let trails = "trails"
    static let identifier = "identifier"
    static let title = "title"
    static let filename = "filename"
    static let geoJSON = "geoJSON"
    static let lastUpdated = "lastUpdated"
}

struct ServiceBuilder: ParsableCommand {
    static let configuration = CommandConfiguration(abstract: "Tool for building consumable json files for the bike-omaha app",
                                                    subcommands: [Build.self])
}

extension ServiceBuilder {
    struct Build: ParsableCommand {
        @Argument(help: "The relative file path to the trail metadata json file.  Must include filename")
        var metadataPath: String
        
        @Argument(help: "The relative path to the directory containing the individual trail files")
        var sourcesDirectory: String
        
        @Option(help: "The relative path to the file where the output json will be written to.  Must include filename")
        var outputPath: String = "output.json"
        
        private func getTimestamp() -> String {
            let rfc3339DateFormatter = DateFormatter()
            rfc3339DateFormatter.locale = Locale(identifier: "en_US_POSIX")
            rfc3339DateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"
            rfc3339DateFormatter.timeZone = TimeZone(secondsFromGMT: 0)
            return rfc3339DateFormatter.string(from: Date())
        }
        
        func run() {
            let pwd = "file://" + FileManager.default.currentDirectoryPath
            
            let outputDir = "\(pwd)/\(outputPath)/"
            guard let outputURL = URL(string: outputDir) else {
                print("✘ Failed to create output URL")
                return
            }
            
            let sourcesDir = "\(pwd)/\(sourcesDirectory)"
            
            guard let metadataPath = URL(string: "\(pwd)/\(metadataPath)") else {
                print("trails-metadata.json must be located in the same directory as this script")
                return
            }
            
            guard let metadataFileData = try? Data(contentsOf: metadataPath),
                  let metadataDict = try? JSONSerialization.jsonObject(with: metadataFileData, options: .allowFragments) as? [String: Any] else {
                print("✘ Failed to decode the \(metadataPath) file into a dictionary")
                return
            }
            
            guard let trailsJson = metadataDict[Keys.trails] as? [[String: Any]] else {
                print("✘ Failed to parse trails array from trails-metadata.json")
                return
            }
            
            // for each trail metadata object, grab the source file and insert
            // the contents into the metadata object under the key 'featureCollection'
            var output: [[String: Any]] = []
            print("Building trail objects...")
            for trailMetadata in trailsJson {
                guard let sourceFilename = trailMetadata[Keys.filename] as? String,
                      let sourceURL = URL(string: sourcesDir + sourceFilename) else {
                    print("    ✘ Failed to parse filename for \(trailMetadata)")
                    continue
                }
                
                guard let sourceData = try? Data(contentsOf: sourceURL),
                      let sourceDict = try? JSONSerialization.jsonObject(with: sourceData, options: .mutableContainers) as? [String: Any] else {
                    print("    ✘ Failed to deserialize source file for \(sourceFilename)")
                    continue
                }
                
                let name = trailMetadata[Keys.title] as? String ?? sourceFilename
                print("    ✔︎ \(name)")
                var trailOutput = trailMetadata
                trailOutput.removeValue(forKey: Keys.filename)
                trailOutput[Keys.geoJSON] = sourceDict
                output.append(trailOutput)
            }
            
            guard !output.isEmpty else {
                print("✘ Failed to create any trail objects")
                return
            }
            
            // Create the final form of the output dictionary
            let finalOutputDict: [String: Any] = [Keys.lastUpdated: getTimestamp(), Keys.trails: output]
            
            // write the output dictionary to a json encoded file
            guard let jsonData = try? JSONSerialization.data(withJSONObject: finalOutputDict) else {
                print("✘ Failed to serialize output json")
                return
            }
            
            let jsonString = String(data: jsonData, encoding: .utf8)
            do {
                try jsonString?.write(to: outputURL, atomically: true, encoding: .utf8)
            } catch {
                print(error)
                return
            }
            
            print("Output written successfully to \(outputURL.absoluteString)")
        }
    }
}

ServiceBuilder.main()

#!/usr/bin/swift
import Foundation

let kTrails = "trails"
let ktitle = "title"
let kIdentifier = "identifier"
let kFilename = "filename"
let kGeoJSON = "geoJSON"

let pwd = "file://" + FileManager.default.currentDirectoryPath

// json files must be located in a subdirectory
// of the script's home directory called 'sources'
let sourcesDir = "\(pwd)/sources/"

let outputDir = "\(pwd)/output/"
guard let outputDirURL = URL(string: outputDir), let outputPath = URL(string: "\(outputDir)trails.json") else {
    print("failed to create output URL")
    exit(0)
}

// trails-metadata.json must be located in the
// script's home directory
guard let metadataPath = URL(string: "\(pwd)/trails-metadata.json") else {
    print("trails-metadata.json must be located in the same directory as this script")
    exit(0)
}

guard let metadataFileData = try? Data(contentsOf: metadataPath),
    let metadataDict = try? JSONSerialization.jsonObject(with: metadataFileData, options: .allowFragments) as? [String: Any] else {
        print("Failed to decode the \(metadataPath) file into a dictionary")
        exit(0)
}

guard let trailsJson = metadataDict[kTrails] as? [[String: Any]] else {
    print("Failed to parse trails array from trails-metadata.json")
    exit(0)
}

// for each trail metadata object, grab the source file and insert
// the contents into the metadata object under the key 'featureCollection'
var output: [[String: Any]] = []

for trailMetadata in trailsJson {
    guard let sourceFilename = trailMetadata[kFilename] as? String,
        let sourceURL = URL(string: sourcesDir + sourceFilename) else {
            print("Failed to parse filename for \(trailMetadata)")
            continue
    }
    
    guard let sourceData = try? Data(contentsOf: sourceURL),
        let sourceDict = try? JSONSerialization.jsonObject(with: sourceData, options: .mutableContainers) as? [String: Any] else {
            print("Failed to deserialize source file for \(sourceFilename)")
            continue
    }
    
    var trailOutput = trailMetadata
    trailOutput.removeValue(forKey: kFilename)
    trailOutput[kGeoJSON] = sourceDict
    output.append(trailOutput)
}

guard !output.isEmpty else {
    print("Failed to create any trail objects")
    exit(0)
}

// write the output dictionary to a json encoded file
let finalOutputDict: [String: Any] = [kTrails: output]
guard let jsonData = try? JSONSerialization.data(withJSONObject: finalOutputDict) else {
    print("Failed to serialize output json")
    exit(0)
}

let jsonString = String(data: jsonData, encoding: .utf8)
do {
    try FileManager.default.createDirectory(at: outputDirURL, withIntermediateDirectories: true)
    try jsonString?.write(to: outputPath, atomically: true, encoding: .utf8)
} catch {
    print(error)
    exit(0)
}

print("Output written successfully to \(outputPath.absoluteString)")

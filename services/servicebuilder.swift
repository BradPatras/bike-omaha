#!/usr/bin/swift
import Foundation 

let pwd = FileManager.default.currentDirectoryPath

// json files must be located in a subdirectory
// of the script's home directory called 'sources'
let sourcesDir: String = "\(pwd)/sources/"

let enumerator = FileManager.default.enumerator(atPath: sourcesDir)

var features: [String] = []
while let filename = enumerator?.nextObject() as? String {
	guard filename.hasSuffix(".json") else { continue }
	guard let filePath = URL(string: "file://" + sourcesDir + filename) else { continue }
	do {
		let text = try String(contentsOf: filePath, encoding: .utf8)
		features.append(text)
	} catch {
		print(error)
	}
}

let outputPath = "file://" + pwd + "/output.json"
try .write(to: outputPath, atomically: false, encoding: .utf8)

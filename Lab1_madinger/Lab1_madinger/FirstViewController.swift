//
//  FirstViewController.swift
//  Lab1_madinger
//
//  Created by Dilara Madinger on 2/1/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    @IBOutlet weak var myPicker: UIPickerView!
    @IBOutlet weak var myImage: UIImageView!
    
    let characterComponent = 0
    let optionsComponent = 1
    var wholePlist = [String: [String: String]]()
    var characterKey = [String]()
    var characterAttrDict = [String: String]()
    var attrKey = [String]()
    var image = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathURL = Bundle.main.url(forResource: "myData", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                wholePlist = try plistdecoder.decode([String: [String: String]].self, from: data)
                characterKey = Array(wholePlist.keys)
                characterAttrDict = wholePlist[characterKey[0]]! as [String: String]
                attrKey = Array(characterAttrDict.keys)
                image = characterAttrDict[attrKey[0]]! as String
            } catch {
                print(error)
            }
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == characterComponent {
            return characterKey.count
        } else {
            return characterAttrDict.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == characterComponent {
            return characterKey[row]
        } else {
            return attrKey[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == characterComponent {
            let selectedCharacter = characterKey[row]
            characterAttrDict = wholePlist[selectedCharacter]!
            attrKey = Array(characterAttrDict.keys)
            myPicker.reloadComponent(optionsComponent)
            myPicker.selectRow(0, inComponent: optionsComponent, animated: true)
        }
        _ = pickerView.selectedRow(inComponent: characterComponent)
        let optionsRow = pickerView.selectedRow(inComponent: optionsComponent)
        myImage.image = UIImage(named: characterAttrDict[attrKey[optionsRow]]!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}


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
    var characterOptions = [String: [String]]()
    var characters = [String]()
    var options = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathURL = Bundle.main.url(forResource: "myData", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                characterOptions = try plistdecoder.decode([String: [String]].self, from: data)
                characters = Array(characterOptions.keys)
                options = characterOptions[characters[0]]! as [String]
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
            return characters.count
        } else {
            return options.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == characterComponent {
            return characters[row]
        } else {
            return options[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == characterComponent {
            let selectedCharacter = characters[row]
            options = characterOptions[selectedCharacter]!
            myPicker.reloadComponent(optionsComponent)
            myPicker.selectRow(0, inComponent: optionsComponent, animated: true)
        }
        let characterRow = pickerView.selectedRow(inComponent: characterComponent)
        let optionsRow = pickerView.selectedRow(inComponent: optionsComponent)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}


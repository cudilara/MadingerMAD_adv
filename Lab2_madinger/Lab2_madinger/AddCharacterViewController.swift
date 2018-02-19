//
//  AddCharacterViewController.swift
//  Lab2_madinger
//
//  Created by Dilara Madinger on 2/19/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class AddCharacterViewController: UIViewController {
    var addedCharacter = String()
    @IBOutlet weak var characterTextfield: UITextField!
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneSegue"{
            //only add a character if there is text in the textfield
            if ((characterTextfield.text?.isEmpty) == false){
                addedCharacter=characterTextfield.text!
            }
        }
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

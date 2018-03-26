//
//  AddTripViewController.swift
//  Lab5_madinger
//
//  Created by dilara on 3/26/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class AddTripViewController: UIViewController {
    @IBOutlet weak var nameInput: UITextField!
    @IBOutlet weak var urlInput: UITextField!
    var addedName = String()
    var addedUrl = String()
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AddTripSegue"{
            //only add a country if there is text in the textfield
            if ((nameInput.text?.isEmpty) == false){
                addedName=nameInput.text!
            }
            if ((urlInput.text?.isEmpty) == false){
                addedUrl=urlInput.text!
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

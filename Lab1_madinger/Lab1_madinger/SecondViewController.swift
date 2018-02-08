//
//  SecondViewController.swift
//  Lab1_madinger
//
//  Created by Dilara Madinger on 2/1/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit
// ??????where to find the names of these apps????
class SecondViewController: UIViewController {
    @IBAction func gotomusic(_ sender: UIButton) {
        if(UIApplication.shared.canOpenURL(URL(string: "spotify://")!)){
            UIApplication.shared.open(URL(string: "spotify://")!, options: [:], completionHandler: nil)
        }else {
            if(UIApplication.shared.canOpenURL(URL(string: "music://")!)){
                UIApplication.shared.open(URL(string: "music://")!, options: [:], completionHandler: nil)
            } else {
                UIApplication.shared.open(URL(string: "https://en.wikipedia.org/wiki/Guardians_of_the_Galaxy_(film)")!, options: [:], completionHandler: nil)
            }
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}


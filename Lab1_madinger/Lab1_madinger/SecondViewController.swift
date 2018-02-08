//
//  SecondViewController.swift
//  Lab1_madinger
//
//  Created by Dilara Madinger on 2/1/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit
class SecondViewController: UIViewController {
    @IBAction func gotomusic(_ sender: UIButton) {
        if(UIApplication.shared.canOpenURL(URL(string: "Youtube://ToCB4ZnYYJs")!)){
            UIApplication.shared.open(URL(string: "Youtube://ToCB4ZnYYJs")!, options: [:], completionHandler: nil)
        }else {
            if(UIApplication.shared.canOpenURL(URL(string: "music://")!)){
                UIApplication.shared.open(URL(string: "music://")!, options: [:], completionHandler: nil)
            } else {
                UIApplication.shared.open(URL(string: "https://www.youtube.com/watch?v=vfWv7j4FIxQ")!, options: [:], completionHandler: nil)
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


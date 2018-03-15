//
//  ViewController.swift
//  midtermMadinger
//
//  Created by Dilara Madinger on 3/15/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    var restaurantList = Restaurants()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathURL = Bundle.main.url(forResource: "myRestaurants", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                    let data = try Data(contentsOf: pathURL)
                    restaurantList.allRestaurants = try plistdecoder.decode([[String: String]].self, from: data)
                    print(restaurantList.allRestaurants)
                } catch {
                    print(error)
                }
            }
        }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}


//
//  Trips.swift
//  Lab5_madinger
//
//  Created by dilara on 3/26/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import Foundation
import RealmSwift

class Trips: Object {
    @objc dynamic var name = ""
//    @objc dynamic var url = ""
    @objc dynamic var done = false
}

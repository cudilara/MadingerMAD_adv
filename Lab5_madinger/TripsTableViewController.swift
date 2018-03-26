//
//  TripsTableViewController.swift
//  
//
//  Created by dilara on 3/26/18.
//

import UIKit
import RealmSwift

class TripsTableViewController: UITableViewController {
    var realm : Realm!
    var tripList: Results<Trips>{
        get {
            return realm.objects(Trips.self)
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        do {
            realm = try Realm()
        } catch let error {
            print(error.localizedDescription)
        }
        
        print(Realm.Configuration.defaultConfiguration.fileURL!)
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return tripList.count
    }

   
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        let item = tripList[indexPath.row]
        cell.textLabel!.text = item.name
//        cell.detailTextLabel!.text = item.url
        cell.accessoryType = item.done ? .checkmark : .none
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = tripList[indexPath.row]
        try! self.realm.write {
            item.done = !item.done
        }
        tableView.reloadRows(at: [indexPath], with: .automatic)
    }
    
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            let item = tripList[indexPath.row]
            try! self.realm.write {
                self.realm.delete(item)
            }
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
        }
    }
    
    @IBAction func addTripItem(_ sender: UIBarButtonItem) {
        let addalert = UIAlertController(title: "New Adventure!", message: "Add a new trip to your list", preferredStyle: .alert)
        addalert.addTextField(configurationHandler: {(UITextField) in
        })
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
        addalert.addAction(cancelAction)
        let addItemAction = UIAlertAction(title: "Add", style: .default, handler: {(UIAlertAction)in
            let newitem = addalert.textFields![0]
//            let newitemUrl = addalert.textFields![1]
            let newTripItem = Trips()
            newTripItem.name = newitem.text!
//            newTripItem.url = newitemUrl.text!
            newTripItem.done = false
            
            do {
                try self.realm.write({
                    self.realm.add(newTripItem)
                    self.tableView.insertRows(at: [IndexPath.init(row: self.tripList.count-1, section:0)], with: .automatic)
                })
            } catch let error{
                print(error.localizedDescription)
            }
        })
        addalert.addAction(addItemAction)
        present(addalert, animated: true, completion: nil)
    }
    /*
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        if segue.identifier=="doneSegue"{
            let source = segue.source as! AddTripViewController
            if ((source.addedName.isEmpty) == false){
                do {
                    try self.realm.write({
                        self.realm.add((source.addedName as? Trips)!)
                        self.tableView.insertRows(at: [IndexPath.init(row: self.tripList.count-1, section:0)], with: .automatic)
                    })
                } catch let error{
                    print(error.localizedDescription)
                }
            }
        }
    }
     */
}

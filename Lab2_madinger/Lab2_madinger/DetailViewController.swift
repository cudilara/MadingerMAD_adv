//
//  DetailTableViewController.swift
//  Lab2_madinger
//
//  Created by Dilara Madinger on 2/19/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//


// Cannot implement data persistence.
// Data persistence component breaks at line 42.
// Data file is written, but is not loaded properly.

import UIKit

class DetailViewController: UITableViewController {
    var characters = [String]()
    var selectedCreature = 0
    var creaturesListDetail = Creatures()
    var searchController : UISearchController!
    let newDataFile = "userData.plist"
    
    override func viewWillAppear(_ animated: Bool) {
        let pathURL:URL?
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        let dataFileURL = docDir.appendingPathComponent(newDataFile)
        if FileManager.default.fileExists(atPath: dataFileURL.path){
            pathURL = dataFileURL
        } else {
            // URL for our plist
            pathURL = Bundle.main.url(forResource: "creatures", withExtension: "plist")
        }
        
        //creates a property list decoder object
        let plistdecoder = PropertyListDecoder()
        do {
            let data = try Data(contentsOf: pathURL!)
            // This is where data persistence breaks!
            // Gives warning:
            // typeMismatch(Swift.Dictionary<Swift.String, Any>, Swift.DecodingError.Context(codingPath: [], debugDescription: "Expected to decode Dictionary<String, Any> but found an array instead.", underlyingError: nil))
            creaturesListDetail.creaturesData = try plistdecoder.decode([String: [String]].self, from: data)
            creaturesListDetail.creatures = Array(creaturesListDetail.creaturesData.keys)
        } catch {
            // handle error
            print(error)
        }
        
        creaturesListDetail.creatures = Array(creaturesListDetail.creaturesData.keys)
        let chosenCreature = creaturesListDetail.creatures[selectedCreature]
        characters = creaturesListDetail.creaturesData[chosenCreature]! as [String]
        
        let resultsController = SearchResultsController()
        resultsController.allwords = characters
        searchController = UISearchController(searchResultsController: resultsController)
        
        searchController.searchBar.placeholder = "Enter a search word"
        searchController.searchBar.sizeToFit()
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController
        
        let app = UIApplication.shared
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.applicationWillResignActive(_:)), name: NSNotification.Name.UIApplicationWillResignActive, object: app)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        //For data persistence
//        let app = UIApplication.shared
//        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.applicationWillResignActive(_:)), name: NSNotification.Name.UIApplicationWillResignActive, object: app)
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
//         self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    @objc func applicationWillResignActive(_ notification: NSNotification){
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0]
        print("docDir is  ", docDir)
        let dataFileURL = docDir.appendingPathComponent(newDataFile)
        print("dataFileURL is ", dataFileURL)
        let plistencoder = PropertyListEncoder()
        plistencoder.outputFormat = .xml
        do{
            let data = try plistencoder.encode(characters)
            try data.write(to: dataFileURL)
        } catch {
            print(error)
        }
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
        return characters.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for: indexPath)
        cell.textLabel?.text = characters[indexPath.row]
        return cell
    }

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            //Delete the creature from the array
            characters.remove(at: indexPath.row)
            let chosenCreature = creaturesListDetail.creatures[selectedCreature]
            //Delete from the data model instance
            creaturesListDetail.creaturesData[chosenCreature]?.remove(at: indexPath.row)
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    
    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        if segue.identifier=="doneSegue"{
            let source = segue.source as! AddCharacterViewController
            //only add a character if there is text in the textfield
            if ((source.addedCharacter.isEmpty) == false){
                //add country to the array
                characters.append(source.addedCharacter)
                tableView.reloadData()
                let chosenCreature = creaturesListDetail.creatures[selectedCreature]
                //add country to our data model instance
                creaturesListDetail.creaturesData[chosenCreature]?.append(source.addedCharacter)
            }
        }
    }

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

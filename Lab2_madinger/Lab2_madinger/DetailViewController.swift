//
//  DetailTableViewController.swift
//  Lab2_madinger
//
//  Created by Dilara Madinger on 2/19/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class DetailViewController: UITableViewController {
    var characters = [String]()
    var selectedCreature = 0
    var creaturesListDetail = Creatures()
    var searchController : UISearchController!
    
    override func viewWillAppear(_ animated: Bool) {
        creaturesListDetail.creatures = Array(creaturesListDetail.creaturesData.keys)
        let chosenCreature = creaturesListDetail.creatures[selectedCreature]
        characters = creaturesListDetail.creaturesData[chosenCreature]! as [String]
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        let resultsController = SearchResultsController()
        resultsController.allwords = characters
        searchController = UISearchController(searchResultsController: resultsController)
        searchController.searchBar.placeholder = "Enter a search word"
        searchController.searchBar.sizeToFit()
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
//         self.navigationItem.rightBarButtonItem = self.editButtonItem
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

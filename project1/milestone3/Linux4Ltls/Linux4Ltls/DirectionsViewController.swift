//
//  DirectionsViewController.swift
//  Linux4Ltls
//
//  Created by Dilara Madinger on 3/8/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class DirectionsViewController: UITableViewController {
    var directionsList = Directions()
    

    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathDirURL = Bundle.main.url(forResource: "Directions", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathDirURL)
                directionsList.fullDirectionsData = try plistdecoder.decode([String: [String: String]].self, from: data)
                directionsList.directionsLabel = Array(directionsList.fullDirectionsData.keys)
                directionsList.textImageDict = directionsList.fullDirectionsData[directionsList.directionsLabel[0]]! as [String: String]
                directionsList.dirText = Array(directionsList.textImageDict.keys)
                directionsList.image = directionsList.textImageDict[directionsList.dirText[0]]! as String
            } catch {
                print(error)
            }
        }
        navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return directionsList.directionsLabel.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellDirections", for: indexPath)
        cell.textLabel?.text = directionsList.directionsLabel[indexPath.row]
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "DirectionsDetailSegue" {
            let indexPath = tableView?.indexPath(for: sender as! UITableViewCell)
            
            let selectedDir = directionsList.directionsLabel[indexPath![1]]
            directionsList.textImageDict = directionsList.fullDirectionsData[selectedDir]!
            directionsList.dirText = Array(directionsList.textImageDict.keys)
            let detailVC = segue.destination as! DirectionsDetailViewController
            detailVC.directionsTxt = directionsList.dirText[0]
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    

//    override func numberOfSections(in tableView: UITableView) -> Int {
//        // #warning Incomplete implementation, return the number of sections
//        return 0
//    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

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

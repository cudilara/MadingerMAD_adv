//
//  MasterViewController.swift
//  Lab4_madinger
//
//  Created by Dilara Madinger on 2/28/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit

class MasterViewController: UITableViewController {

    var detailViewController: DetailViewController? = nil
    var characters = [Character]()


    override func viewDidLoad() {
        super.viewDidLoad()
        loadjson()
    }

    func loadjson(){
        let urlPath = "https://swapi.co/api/people/"
        guard let url = URL(string: urlPath)
            else {
                print("url error")
                return
        }
        
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            print(statusCode)
            guard statusCode == 200
                else {
                    print("file download error")
                    return
            }
            //download successful
            print("download complete")
            DispatchQueue.main.async {self.parsejson(data!)}
        })
        //must call resume to run session
        session.resume()
    }
    
    func parsejson(_ data: Data){
        do {
            // get json data
            let json = try JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.allowFragments) as! [String:Any]
            //get all results
            let allresults = json["results"] as! [[String:Any]]
            //add results to objects
            for result in allresults {
                //check that data exists
                guard let newname = result["name"]! as? String,
                    let newheight = result["height"] as? String,
                    let newmass = result["mass"] as? String,
                    let newurl = result["url"]!as? String
                    else {
                        continue
                }
                //create new object
                let newcharacter = Character(name: newname, height: newheight, mass: newmass, url: newurl)
                //add object to array
                self.characters.append(newcharacter)
            }
            //handle thrown error
        } catch {
            print("Error with JSON: \(error)")
            return
        }
        //reload the table data after the json data has been downloaded
        tableView.reloadData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.clearsSelectionOnViewWillAppear = self.splitViewController!.isCollapsed
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Segues

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            if let indexPath = self.tableView.indexPathForSelectedRow {
                let character = characters[indexPath.row]
                let name = character.name
                let controller = (segue.destination as! UINavigationController).topViewController as! DetailViewController
                switch name{
                case "Luke Skywalker":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Luke_Skywalker"
                case "C-3PO":
                    controller.detailItem = "https://en.wikipedia.org/wiki/C_3PO"
                case "R2-D2":
                    controller.detailItem = "https://en.wikipedia.org/wiki/R2_D2"
                case "Darth Vader":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Darth_Vader"
                case "Leia Organa":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Leia_Organa"
                case "Owen Lars":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Skywalker_family"
                case "Beru Whitesun Lars":
                    controller.detailItem = "https://nl.wikipedia.org/wiki/Beru_Whitesun_Lars"
                case "R5-D4":
                    controller.detailItem = "https://screenrant.com/star-wars-r5-d4-bad-motivator-r2-d2-rebels/"
                case "Biggs Darklighter":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Biggs_Darklighter"
                case "Obi-Wan Kenobi":
                    controller.detailItem = "https://en.wikipedia.org/wiki/Obi_Wan_Kenobi"
                default:
                    controller.detailItem = "https://en.wikipedia.org/wiki/Star_Wars"
                }
                controller.title = name
                controller.navigationItem.leftBarButtonItem = self.splitViewController?.displayModeButtonItem
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }
    }

    // MARK: - Table View

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return characters.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        let character = characters[indexPath.row]
        cell.textLabel!.text = character.name
        cell.detailTextLabel!.text = String(character.height) + " cm and " + String(character.mass) + " kg"
        return cell
    }
}


import UIKit

class ViewController: UITableViewController {
    var creaturesList = Creatures()
    let newDataFile = "userData.plist"

    override func viewDidLoad() {
        super.viewDidLoad()
        let app = UIApplication.shared
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.applicationWillResignActive(_:)), name: NSNotification.Name.UIApplicationWillResignActive, object: app)
        // URL for our plist
        if let pathURL = Bundle.main.url(forResource: "creatures", withExtension: "plist"){
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decodes the property list
                creaturesList.creaturesData = try plistdecoder.decode([String: [String]].self, from: data)
                creaturesList.creatures = Array(creaturesList.creaturesData.keys)
            } catch {
                // handle error
                print(error)
            }
        }
        
        //enables large titles
        navigationController?.navigationBar.prefersLargeTitles = true
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
            let data = try plistencoder.encode(creaturesList.creaturesData)
            try data.write(to: dataFileURL)
        } catch {
            print(error)
        }
    }
    
    //Required methods for UITableViewDataSource
    //Number of rows in the section
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return creaturesList.creatures.count
    }
    
    // Displays table view cells
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        //configure the cell
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for: indexPath)
        cell.textLabel?.text = creaturesList.creatures[indexPath.row]
        return cell
    }
    
    //Handles segues to other view controllers
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "charactersegue" {
            let detailVC = segue.destination as! DetailViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            //sets the data for the destination controller
            detailVC.title = creaturesList.creatures[indexPath.row]
            detailVC.creaturesListDetail=creaturesList
            detailVC.selectedCreature = indexPath.row
        } //for detail disclosure
//        else if segue.identifier == "charactersegue"{
//            let infoVC = segue.destination as! CreatureInfoTableViewController
//            let editingCell = sender as! UITableViewCell
//            let indexPath = tableView.indexPath(for: editingCell)
//            infoVC.name = creaturesList.creatures[indexPath!.row]
//            let characters = creaturesList.creatureData[infoVC.name]! as [String]
//            infoVC.number = String(characters.count)
//        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}


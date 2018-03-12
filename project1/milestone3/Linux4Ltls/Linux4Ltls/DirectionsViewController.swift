import UIKit

class DirectionsViewController: UITableViewController {
    var directionsList = Directions()
    

    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathDirURL = Bundle.main.url(forResource: "Directions", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            self.navigationItem.rightBarButtonItem=self.editButtonItem
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
            let selectedTxt = directionsList.dirText[0]
            let detailVC = segue.destination as! DirectionsDetailViewController
            detailVC.directionsTxt = directionsList.dirText[0]
            detailVC.image = UIImage(named: directionsList.textImageDict[selectedTxt]!)
            detailVC.mylabel = directionsList.directionsLabel[indexPath![1]]
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

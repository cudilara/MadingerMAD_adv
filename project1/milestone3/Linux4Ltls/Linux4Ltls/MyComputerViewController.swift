import UIKit

class MyComputerViewController: UITableViewController {
    var mycomputerList = MyComputer()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathDirURL = Bundle.main.url(forResource: "MyComputer", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathDirURL)
                mycomputerList.fullMyComputerData = try plistdecoder.decode([String: [String: String]].self, from: data)
                mycomputerList.myComputerLabel = Array(mycomputerList.fullMyComputerData.keys)
                mycomputerList.textImageDict = mycomputerList.fullMyComputerData[mycomputerList.myComputerLabel[0]]! as [String: String]
                mycomputerList.myComputerText = Array(mycomputerList.textImageDict.keys)
                mycomputerList.image = mycomputerList.textImageDict[mycomputerList.myComputerText[0]]! as String
            } catch {
                print(error)
            }
        }
        navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mycomputerList.myComputerLabel.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellMyComputer", for: indexPath)
        cell.textLabel?.text = mycomputerList.myComputerLabel[indexPath.row]
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "DirectionsMyComputerSegue" {
            let indexPath = tableView?.indexPath(for: sender as! UITableViewCell)
            
            let selectedDir = mycomputerList.myComputerLabel[indexPath![1]]
            mycomputerList.textImageDict = mycomputerList.fullMyComputerData[selectedDir]!
            mycomputerList.myComputerText = Array(mycomputerList.textImageDict.keys)
            let detailVC = segue.destination as! MyComputerDetailViewController
            let selectedTxt = mycomputerList.myComputerText[0]
            detailVC.myComputerTxt = mycomputerList.myComputerText[0]
            detailVC.mycompimage = UIImage(named: mycomputerList.textImageDict[selectedTxt]!)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}


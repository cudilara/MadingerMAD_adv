import UIKit

class NetworkViewController: UITableViewController {
    var networkList = Network()
    

    override func viewDidLoad() {
        super.viewDidLoad()
        if let pathDirURL = Bundle.main.url(forResource: "Network", withExtension: "plist"){
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathDirURL)
                networkList.fullNetworkData = try plistdecoder.decode([String: [String: String]].self, from: data)
                networkList.networkLabel = Array(networkList.fullNetworkData.keys)
                networkList.textImageDict = networkList.fullNetworkData[networkList.networkLabel[0]]! as [String: String]
                networkList.networkText = Array(networkList.textImageDict.keys)
                networkList.image = networkList.textImageDict[networkList.networkText[0]]! as String
            } catch {
                print(error)
            }
        }
        navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return networkList.networkLabel.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellNetwork", for: indexPath)
        cell.textLabel?.text = networkList.networkLabel[indexPath.row]
        return cell
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "NetworkDetailSegue" {
            let indexPath = tableView?.indexPath(for: sender as! UITableViewCell)
            let selectedDir = networkList.networkLabel[indexPath![1]]
            networkList.textImageDict = networkList.fullNetworkData[selectedDir]!
            networkList.networkText = Array(networkList.textImageDict.keys)
            let selectedTxt = networkList.networkText[0]
            let detailVC = segue.destination as! NetworkDetailViewController
            detailVC.networkTxt = networkList.networkText[0]
            detailVC.networkImage = UIImage(named: networkList.textImageDict[selectedTxt]!)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

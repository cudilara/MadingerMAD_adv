import UIKit

class DirectionsDetailViewController: UIViewController {
    @IBOutlet weak var sectionText: UILabel!
    var directionsName : String?
    var selectedDirections = 0
    var selectedPair = [String : String]()
    var mydirections = Directions()
    
    override func viewWillAppear(_ animated: Bool){
        if let name = directionsName {
            sectionText.text = name
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

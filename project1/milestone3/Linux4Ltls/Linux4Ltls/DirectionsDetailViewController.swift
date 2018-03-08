import UIKit

class DirectionsDetailViewController: UIViewController {
    @IBOutlet weak var sectionText: UILabel!
    var directionsTxt : String?
    
    override func viewWillAppear(_ animated: Bool){
        if let txt = directionsTxt {
            sectionText.text = txt
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

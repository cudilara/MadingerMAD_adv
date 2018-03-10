import UIKit

class NetworkDetailViewController: UIViewController {
    @IBOutlet weak var sectionText: UITextView!
    var networkTxt : String?
    
    override func viewWillAppear(_ animated: Bool){
        if let txt = networkTxt {
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

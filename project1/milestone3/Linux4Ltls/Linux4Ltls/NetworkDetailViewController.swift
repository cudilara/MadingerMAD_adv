import UIKit

class NetworkDetailViewController: UIViewController {
    @IBOutlet weak var myImage: UIImageView!
    @IBOutlet weak var sectionText: UITextView!
    var networkTxt : String?
    var networkImage : UIImage?
    var mylabel : String?
    
    override func viewWillAppear(_ animated: Bool){
        self.navigationItem.title = mylabel
        if let txt = networkTxt {
            sectionText.text = txt
            myImage.image = networkImage
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

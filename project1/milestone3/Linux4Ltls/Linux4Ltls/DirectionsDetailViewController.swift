import UIKit

class DirectionsDetailViewController: UIViewController {
    @IBOutlet weak var sectionText: UITextView!
    @IBOutlet weak var myImage: UIImageView!
    var directionsTxt : String?
    var image : UIImage?
    var mylabel : String?
    
    override func viewWillAppear(_ animated: Bool){
        self.navigationItem.title = mylabel
        if let txt = directionsTxt {
            sectionText.text = txt
            myImage.image = image
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

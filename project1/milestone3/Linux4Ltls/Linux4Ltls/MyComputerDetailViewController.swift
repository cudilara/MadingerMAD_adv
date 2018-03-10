import UIKit

class MyComputerDetailViewController: UIViewController {
    @IBOutlet weak var myImage: UIImageView!
    @IBOutlet weak var sectionText: UITextView!
    var myComputerTxt : String?
    var mycompimage : UIImage?
    
    override func viewWillAppear(_ animated: Bool){
                if let txt = myComputerTxt {
                    sectionText.text = txt
                    myImage.image = mycompimage
                }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}


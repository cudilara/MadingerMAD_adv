//
//  DetailViewController.swift
//  Lab4_madinger
//
//  Created by Dilara Madinger on 2/28/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit
import WebKit

class DetailViewController: UIViewController, WKNavigationDelegate {
    @IBOutlet weak var webview: WKWebView!
    @IBOutlet weak var webspinner: UIActivityIndicatorView!
    var detailItem: String?
    
    func configureView() {
        if let url = detailItem{
            if url != "null"{
                loadWebPage(url)
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        webview.navigationDelegate = self
        configureView()
    }
    
    func loadWebPage(_ urlString: String){
        //the urlString should be a propery formed url
        //creates a NSURL object
        print("URLSTRING", urlString)
        let url = URL(string: urlString)
        //create a NSURLRequest object
        let request = URLRequest(url: url!)
        //load the NSURLRequest object in our web view
        webview.load(request)
    }
/*
    //WKNavigationDelegate method that is called when a web page begins to load
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        webSpinner.startAnimating()
    }
    
    //WKNavigationDelegate method that is called when a web page loads successfully
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        webSpinner.stopAnimating()
    }
  */
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}


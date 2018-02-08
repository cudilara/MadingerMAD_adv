//
//  ThirdViewController.swift
//  Lab1_madinger
//
//  Created by Dilara Madinger on 2/8/18.
//  Copyright © 2018 Dilara Madinger. All rights reserved.
//

import UIKit
import AVFoundation

class ThirdViewController: UIViewController, AVAudioPlayerDelegate, AVAudioRecorderDelegate {
    @IBOutlet weak var recordButton: UIButton!
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var stopButton: UIButton!
    var audioPlayer: AVAudioPlayer?
    var audioRecorder: AVAudioRecorder?
    let fileName = "audio.m4a"
    
    @IBAction func RecordAudio(_ sender: UIButton) {
        //if not already recording, start recording
        if audioRecorder?.isRecording == false{
            playButton.isEnabled = false
            stopButton.isEnabled = true
            audioRecorder?.delegate = self
            print("start recording")
            audioRecorder?.record()
        }
    }
    @IBAction func PlayAudio(_ sender: UIButton) {
        //if not recording play audio file
        if audioRecorder?.isRecording == false{
            stopButton.isEnabled = true
            recordButton.isEnabled = false
            
            do {
                try audioPlayer = AVAudioPlayer(contentsOf:
                    (audioRecorder?.url)!)
                audioPlayer!.delegate = self
                audioPlayer!.prepareToPlay() //prepares the audio player for playback by preloading its buffers
                print("start playing")
                audioPlayer!.play() //plays audio file
            } catch let error as NSError {
                print("audioPlayer error: \(error.localizedDescription)")
            }
        }
    }
    @IBAction func StopAudio(_ sender: UIButton) {
        stopButton.isEnabled = false
        playButton.isEnabled = true
        recordButton.isEnabled = true
        //stop recording or playing
        if audioRecorder?.isRecording == true {
            print("stop recording")
            audioRecorder?.stop()
        } else {
            print("stop playing")
            audioPlayer?.stop()
        }
    }
    
    //AVAudioPlayerDelegate method
    //Called when a recording is stopped or has finished due to reaching its time limit
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        recordButton.isEnabled = true
        stopButton.isEnabled = false
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //disable buttons since no audio has been recorded
        playButton.isEnabled = false;
        stopButton.isEnabled = false;
        
        //get path for the audio file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        let audioFileURL = docDir.appendingPathComponent(fileName)
        print(audioFileURL)
        
        let audioSession = AVAudioSession.sharedInstance() //the shared audio session instance
        do {
            //sets he category for recording and playback of audio
            try audioSession.setCategory(AVAudioSessionCategoryPlayAndRecord)
        } catch {
            print("audio session error: \(error.localizedDescription)")
        }
        
        //recorder settings
        let settings = [
            AVFormatIDKey: Int(kAudioFormatMPEG4AAC), //specifies audio codec
            AVSampleRateKey: 12000, //sample rate in hertz
            AVNumberOfChannelsKey: 1, //number of channels
            AVEncoderAudioQualityKey: AVAudioQuality.high.rawValue //audio bit rate
        ]
        
        do {
            //create the AVAudioRecorder instance
            audioRecorder = try AVAudioRecorder(url: audioFileURL, settings: settings)
            audioRecorder?.prepareToRecord()
            print("audio recorder ready")
        } catch {
            print("audio recorder error: \(error.localizedDescription)")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

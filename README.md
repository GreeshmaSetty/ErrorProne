![](logo.png)

# Testautothon 2019 (STeP-IN Summit 2019)


This repository contains the testautothon step-in 2019 summit challenge solution.


## Team Members:
- [Jyothi V Sai]()
- [HariCharan G]()
- [Greeshma Shetty]()
- [Baalaksha]()
- [Raghuveer]()

<u><b>Scenario to be automated</b></u>

###Steps:

1. Open [https://www.youtube.com]
2. Search for "step-inforum"
3. Open "step-in forum" channel
4. Navigate to "Videos" tab
5. Make an API call [http://<<LAB_IP>>/video] and get the video name from the responce.
6. Locate the video fetched from the above step by scrolling to the videos list. Video title should appear in the center of the page.
7. Capture the screenshot when the video is located in the screen
8. Click on the video
9. Change the video quality to 360p
10. Get and list the names of all upcoming videos under ("Up Next") section (right side of the screen) 
11. Write the names of the video fetched from step 10 to a JSON file in te following template:
    ```bash
    {
      "team" : "team-name",
      "video": "search-video-name",
      "upcoming-videos": [
             "sample 1",
             "sample 2",
             "sample 3"
         ]
    }
    ```
12. Post/upload file to the server [http://<LAB IP_/upload]
13. validate the file upload [http://<LAB_IP>results/<responce_recieved from upload>]
14. Generate the test excecution report

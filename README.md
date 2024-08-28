![](logo.png)

# Testautothon 2024 (STeP-IN Summit 2024)


This repository contains the Test'Autothon step-in 2024 summit challenge solution.


## Team Members:
- [Jyothi V Sai]()
- [HariCharan G]()
- [Greeshma Shetty]()
- [Santhosh Narayana]()
- [Atib Hawaldhar]()

<u><b>Scenario to be automated</b></u>

<b>Steps:</b>
STEP1:
1.1. Open [https://www.indianexpress.com]
1.2. Identify and click on a link labelled "India," "Sports," "Business," or "Politics." as shown in the pic below
        ![image](https://github.com/user-attachments/assets/27f3d2cb-379e-4f85-9282-7c433a71b42e)
1.3. From the selected section, locate a news article featuring a carousel (as depicted in the provided reference image).
       ![image](https://github.com/user-attachments/assets/4d176641-c7bc-40b0-b9f8-15f4b1274065)
1.4. For three of the carousel items, extract the following information:
        ●	Headline
        ●	News Link
        ●	Date and Time of Publication
        ●	Team Name (This is your team’s name and not info that you get from the news item)		
        
STEP2:API Interaction and Validation
2.1. Send the extracted data to the backend through an API. The API documentation is available a the below location.
        http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000/docs#/
2.2 Please read the API documentation to understand which API will be used to post the data to the backend.
Note: News date should be in the format of DDMMYYYY for ex if the news was published on 15-Aug-2024, the format should be 15082024
2.3. The API will respond with an Item ID. Use this Item ID to validate the data using the available APIs.

Step 3: 
3.1. Launch the provided APK on a mobile device or emulator and extract the following product details:
        ●	Name
        ●	Description
        ●	Price
        ●	Team Name
3.2. Push these details to the backend and validate them using the API.

Step 4: Automation Setup
4.1. Set up the entire automation solution to be executed from a build/deploy tool of your choice.

Step 5: Reporting
5.1. Create a report detailing the steps, results, and any issues encountered during the contest. The format and fields of the report are to be determined by you as the tester.

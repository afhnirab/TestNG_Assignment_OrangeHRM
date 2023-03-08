# TestNG_Assignment_OrangeHRM

## Scenarios:
  1. Login to orange hrm demo site "https://opensource-demo.orangehrmlive.com/"
  2. Create 2 new employees and save it to a JSON list
  3. Now go to PIM dashboard and search by 1st user name. Assert that the user is found.
  4. Now click on the user from the search table and update id by random userid
  5. Now again search the user by new user id from the PIM dashboard menu and assert that the user is found
  6. Now logout from admin and login with the 2nd user from your JSON list
  7. Now click on My Info menu
  8. Select Gender and Blood Type and save it
  9. Click on contact details and input address and email
  10. Logout the user

## Negative Scenarios:
  1. Admin shall not be able to login using invalid username
  2. Admin can not login with invalid password
  3. Admin shall not be able to create an user without firstname and lastname
  4. It shall not display any records while searching with a full name that is not in the database
  5. Search the updated id with invalid id
  6. Employee shall be able to login using invalid username and invalid password
  7. Employee shall be able to login using invalid username and valid password

## Technologies:
  1. Java
  2. Intellij Community Edition
  3. Gradle
  4. TestNG
  5. Allure
  6. Faker
  7. JSON

## Allure Screenshot
![Screenshot 2023-03-08 202154](https://user-images.githubusercontent.com/61575633/223789987-b89abb73-0a08-4686-bd20-0fa2a2185e9b.png)

![Screenshot 2023-03-08 202213](https://user-images.githubusercontent.com/61575633/223790000-17a1003e-1c11-4b44-a1d9-fbf0b543dc68.png)

## Automation Video:

https://user-images.githubusercontent.com/61575633/223789890-5d83b199-5de4-4eab-9834-c83ef2496c55.mp4








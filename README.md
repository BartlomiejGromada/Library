# Library
## Example 'library' project in Spring Boot (Spring JPA, MicrosoftSQLServer, Spring WEB, Spring Security, Thymeleaf)
### Application provide to users read info about books, authors and categories but for admins CRUD.

**Register form**
![registerForm](images/registerForm.PNG)
![registerResult](images/registerFormResult.PNG)

**Login form**
![loginForm](images/loginForm.PNG)
**Login form incorrect**
![loginIncorrect](images/loginIncorecctForm.PNG)

**There are three types of users who have different views and functionalities:**
1. Not loggin in - only read
![tableNoLogin](images/tableNoLogin.PNG)

2. Logged ROLE_USER - only read, view with login
![tableLoginUser](images/viewUserLogin.PNG)

3. Logged ROLE_ADMIN - CRUD
![tableLoginAdmin](images/tableLogin.PNG)

**CRUD on the example of books**
  * add
  ![addItem](images/addBookForm.PNG)
  ![addItemResult](images/addBookFormResult.PNG)
  * update
  ![addItem](images/updateBookForm.PNG)
  ![addItem](images/afterUpdate.PNG)
  * delete
  ![addItem](images/deleteBook.PNG)
  
**Others tables:**
  * Categories
  ![categoriesTable](images/categoriesTable.PNG)
  * Authors
  ![authorsTable](images/authorsTable.PNG)
  
**System display this view when user want use operation for Admin**
![notPermissionView](images/notPermissionView.PNG)
  

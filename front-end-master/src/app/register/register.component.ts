import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { PopupService } from '../popup.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent  
implements OnInit 
{
  user: any = {
    userName: '',
    userFirstName: '',
    userLastName: '',
    userPassword: '',
    // confirmPassword: ''
  };
  registerForm: any;
  //popupService: any;
  

  constructor(private formBuilder: FormBuilder,private http: HttpClient) { }
  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required, Validators.pattern('.*@gmail\\.com$')],
      userFirstName: ['', Validators.required],
      userLastName: ['', Validators.required],
      userPassword: ['', [Validators.required, Validators.maxLength(8)]]
    });
  }
  
  
  showSuccessPopup: boolean = false;
  showFailurePopup: boolean = false;

  showPassword: boolean = false;

  // onSubmit(registerForm: NgForm) {
  //   if (registerForm.valid && this.user.password === this.user['confirmPassword']) {
  //     // Perform registration logic here, e.g., calling an API

  //     // Show success popup
  //     this.showSuccessPopup = true;
  //   }
  // }

  


  // onSubmit(registerForm: NgForm) {
  //   if (registerForm.valid) {
  //     // Perform registration logic
  //     // Display a success message
  //     alert('Registration successful!');
  //   }


  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  
  onSubmit(registerForm: NgForm) {
    if (registerForm.valid) {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      const options = { headers: headers };

      // Make an HTTP POST request to the backend API
      // this.http.post<any>('http://localhost:9090/registerNewUser', this.user, options).subscribe(
        // this.popupService(this.user).subscribe(  
          this.http.post<any>('http://localhost:8086/api/auth/registerNewUser', this.user, options).subscribe(
        // this.popupService(this.user).subscribe(  
      (response) => {
          console.log('Registration successful!', response);
          
          // this.popupService.showSuccessPopup();
          this.user = {};
          this.showSuccessPopup = true;
          registerForm.resetForm();
          //this.popupService.showSuccessPopup();

         
        },
        (error) => {
          
         // Error callback
         console.log('Registration failed!', error);
         this.showFailurePopup = true;
         //this.popupService.showFailurePopup();
        }
      );
    }
  }


  // onSubmit(registerForm: NgForm) {
  //   if (registerForm.valid) {
  //     this.popupService.registerUser(this.user).subscribe(
  //       (response) => {
  //         // Success callback
  //         console.log('Registration successful!', response);
  //         this.popupService.showSuccessPopup();
  //         this.user = {};
  //         registerForm.resetForm();
  //       },
  //       (error) => {
  //         // Error callback
  //         console.error('Registration failed!', error);
  //         this.popupService.showFailurePopup();
  //       }
  //     );
  //   }
  // }

  closeSuccessPopup(): void {
    this.showSuccessPopup = false;
  }
  closeFailurePopup(): void {
    this.showFailurePopup = false;
  }


  // togglePasswordVisibility(field: string): void {
  //   if (field === 'password') {
  //     this.showPassword = !this.showPassword;
  //   }
  //   else if (field === 'confirmPassword') {
  //     // Handle toggling for the confirm password field if needed
  //   }
  // }

  
 

 }

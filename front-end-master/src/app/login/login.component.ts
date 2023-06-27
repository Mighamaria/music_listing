import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

interface LoginResponse {
  jwtToken: string;
  role: string;
  user : any;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: any = {
    userName: '',
    userPassword: ''
  };
  showPassword: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(loginForm: NgForm) {
    if (loginForm.valid) {
      const { userName, userPassword } = this.user;

      // Make an HTTP request to the authentication endpoint
      this.http.post<LoginResponse>('http://localhost:8086/api/auth/authenticate', { userName, userPassword })
        .subscribe(
          (response: LoginResponse) => {
            // Handle the successful login response
            console.log('Login successful:', response);

            // Reset the form
            loginForm.resetForm();

            // Clear the user object
            this.user = {
              userName: '',
              userPassword: ''
            };

            // Store the token and role in local storage for future requests
            console.log(response.user.role[0]);
  
            localStorage.setItem('jwtToken', response.jwtToken);
            localStorage.setItem('roleName', response.user.role[0].roleName);

            // Redirect to the appropriate dashboard based on the user role
            if (response.user.role[0].roleName === 'Admin') {
              this.router.navigate(['/admin']);
            } else if (response.user.role[0].roleName === 'User') {
              this.router.navigate(['/user']);
            } else {
              console.error('Invalid role:', response.role);
            }
          },
          (error: any) => {
            // Handle the login error
            console.error('Login error:', error);
          }
        );
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
}

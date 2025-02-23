import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../core/service/auth/auth.service';
import { EnumAuth } from '../../shared/enum/enum-auth';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { NotificationComponent } from '../../shared/notification/notification.component';
import { EnumNotificationType } from '../../shared/enum/enum-notification-type';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  passwordVisible = false;
 
  durationInSeconds = 5;
  protected readonly EnumAuth = EnumAuth;
  formLogin: FormGroup = new FormGroup({
    username: new FormControl('vitooooo', [Validators.required, Validators.minLength(8)]),
    password: new FormControl('Vitoooo95.', [Validators.required, Validators.minLength(6), Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&#.])[A-Za-z\d$@$!%*?&#.].{8,30}')])
  });


  constructor(private authservice: AuthService, private _snackBar: MatSnackBar, private router:Router) {

  }

  ngOnInit() {

  }

  get username(): AbstractControl | null {
    return this.formLogin.get('username');
  }

  get password() {
    return this.formLogin.get('password');
  }

  onSubmitLoginForm(event?: string) {
    if(!!event){

      if (event === EnumAuth.Login) {
        this.authservice.login(this.username?.value, this.password?.value).subscribe({
          next: resp => {
            console.log(resp);
            sessionStorage.setItem('token',resp.accessToken);
            this.router.navigate(['/homepage']);
          },
          error: err=>{
            this._snackBar.openFromComponent(NotificationComponent,{
              data: {
                message: 'Accesso non valido. Riprova!',
                type: EnumNotificationType.ERROR,
              },
              duration: this.durationInSeconds * 1000,
            })
          }
        })
      }else if (event === EnumAuth.Register){
         this.authservice.register(this.username?.value, this.password?.value).subscribe({
          next: resp => {
            console.log(resp);
            sessionStorage.setItem('token',resp.accessToken);
            this.router.navigate(['/homepage']);
          },
          error: err=>{
            console.log(err);
            this._snackBar.openFromComponent(NotificationComponent,{
              data: {
                message: 'Registrazione non andata a buon fine. Riprova!',
                type: EnumNotificationType.ERROR,
              },
              duration: this.durationInSeconds * 1000,
            })
          }
        })
      }
    }


  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }

}


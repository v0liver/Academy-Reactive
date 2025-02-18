import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  formLogin: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email,Validators.min(8)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&#.])[A-Za-z\d$@$!%*?&#.].{8,30}')])
  });


  constructor() {

  }

  ngOnInit() {

  }

  get email(): AbstractControl | null {
    return this.formLogin.get('email');
  }

  get password() {
    return this.formLogin.get('password');
  }

  onSubmitLoginForm() {
    if(this.formLogin.valid){
      
    }

  }

}

import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../core/service/auth/auth.service';
import { EnumAuth } from '../../shared/enum/enum-auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  EnumAuth= EnumAuth;
  formLogin: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required,Validators.minLength(8)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&#.])[A-Za-z\d$@$!%*?&#.].{8,30}')])
  });


  constructor(private authservice:AuthService) {

  }

  ngOnInit() {

  }

  get username(): AbstractControl | null {
    return this.formLogin.get('username');
  }

  get password() {
    return this.formLogin.get('password');
  }

  onSubmitLoginForm(event:any) {
    if(event===EnumAuth.Login){
      this.authservice.login(this.username?.value,this.password?.value).subscribe({
        next: resp=>{
          console.log(resp);
        }
      })
    }
    

  }

}

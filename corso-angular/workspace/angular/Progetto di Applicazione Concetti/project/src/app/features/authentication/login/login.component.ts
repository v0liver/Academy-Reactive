import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../shared/services/user.service';
import { User } from '../../../shared/model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      email: ['eve.holt@reqres.in', [Validators.required, Validators.email]],
      password: ['cityslicka', [Validators.required, Validators.minLength(6)]]
    });
  }



  onSubmit() {


    if (this.loginForm.valid) {
      const user: User = {
        email: this.loginForm.get('email')!.value,
        password: this.loginForm.get('password')!.value
      }
      this.userService.login(user).subscribe({
        next: resp => {
          this.router.navigate(['/homepage']);
          console.log('Form submitted', this.loginForm.value);
        },
        error: resp => {
          if (resp.error.error === 'user not found') {
            alert('User non trovato');
          } else {
            alert('problema nel login')
          }

        }
      })

    }
  }

}

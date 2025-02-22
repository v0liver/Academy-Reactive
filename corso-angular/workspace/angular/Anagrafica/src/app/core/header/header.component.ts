import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public router:Router) { }

  ngOnInit() {
  }

  logout(){
    sessionStorage.removeItem('token');
    this.router.navigate(['/']);
  }

  homePage(){
    this.router.navigate(['/homepage']);

  }

  nuovaPersona(){
    this.router.navigate(['/nuova-persona']);
  }
}

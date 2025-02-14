import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../model/user';
import { AsyncSubject, BehaviorSubject, combineLatest, EMPTY, forkJoin, Observable, of, ReplaySubject, Subject, switchMap } from 'rxjs';
import { GenericResponseList } from '../model/generic-response-list';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {

  combo1DataSource?: User;
  combo2DataSource?: User;



  user?: User;

  users: User[] = [];

  obs: any;


  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {

    /*let obs: Observable<number> = of(Math.random());

    obs.subscribe(result => {
      console.log('1° obs', result)
    });

    obs.subscribe(result => {
      console.log('2° obs', result)
    });

    obs.subscribe(result => {
      console.log('3° obs', result)
    });


    obs = of(Math.random());
    obs = of(Math.random());*/

    //subject
    //let subject = new Subject<number>();
    //let subject = new BehaviorSubject<number>(0);
    let subject = new ReplaySubject<number>();
    //let subject = new AsyncSubject<number>();
    subject.next(Math.random());
    subject.next(Math.random());
    subject.next(Math.random());
    subject.next(Math.random());

    subject.subscribe(result => {
      console.log('1° subj', result)
    })

    subject.subscribe(result => {
      console.log('2° subj', result)
    })

    subject.subscribe(result => {
      console.log('3° subj', result)
    })

    //subject.next(Math.random());
    //subject.next(Math.random());
    
    subject.complete();

    const obs: Observable<any>[] = [];

    obs.push(this.userService.findById(1));
    if(Math.random() == 2) {
      obs.push(this.userService.findById(2));
    }
    //forkJoin serve per combinare 2 o più observable in un unico stream di gruppo
    //che parte insieme e, quando tutti hanno finito, si risolve in un'unica esecuzione delle responses
    forkJoin(obs).subscribe( ([resp1, resp2]) => {
      this.combo1DataSource = resp1;
      this.combo2DataSource = resp2;
    });

    //combineLatest serve per combinare 2 o più observable in un unico stream di gruppo
    //che parte insieme e, in qualunque momento almeno un valore su tutti gli stream si risolve
    //si attiva un'esecuzione delle responses cumulative 
    combineLatest(obs).subscribe( ([resp1, resp2]) => {
      this.combo1DataSource = resp1;
      this.combo2DataSource = resp2;
    });

    this.userService.findById(1).subscribe(resp => {
      console.log(resp);
      this.userService.findById(2).subscribe(resp1 => {
        
      });
    });



    this.userService.findCustom().subscribe(response => {
      console.log('response: ', response);
    });





    /*console.log('hai passato il query param opzionale: ', this.route.snapshot.queryParamMap.get('param1'));

    this.userService.findById().subscribe({
      next: resp => {console.log(resp); this.user = resp.data},
      error: resp => {console.error(resp);}
    });

    this.userService.search('fausto', 'di iorio', 10).subscribe(response => {
      this.users = response.data;
    });*/
  }
}

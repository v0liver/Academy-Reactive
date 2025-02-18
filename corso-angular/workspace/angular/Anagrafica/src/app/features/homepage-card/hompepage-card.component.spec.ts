/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { HompepageCardComponent } from './hompepage-card.component';

describe('HompepageCardComponent', () => {
  let component: HompepageCardComponent;
  let fixture: ComponentFixture<HompepageCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HompepageCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HompepageCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

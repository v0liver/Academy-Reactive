/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PersonaService } from './persona.service';

describe('Service: Utente', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PersonaService]
    });
  });

  it('should ...', inject([PersonaService], (service: PersonaService) => {
    expect(service).toBeTruthy();
  }));
});

import { Injectable } from '@angular/core';
import { SessionInput } from '../model/session.input.model';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor() {}

  getSessionsByUser(pseudo: string): SessionInput[] {
    // TODO unmock later
    return [
      {
        id: 1,
        nomSession: 'Coupe du monde Qatar',
        dateCreationSession: new Date(),
        pseudoCreateur: 'Francky Vincent',
        matchs: [],
        participants: ['Jo', 'Avrell'],
      },
      {
        id: 2,
        nomSession: 'LDC',
        dateCreationSession: new Date(),
        pseudoCreateur: 'Bob Sinclar',
        matchs: [],
        participants: ['Jack', 'William'],
      },
    ];
  }
}

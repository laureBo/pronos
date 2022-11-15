import { Injectable } from '@angular/core';
import { UserInput } from 'src/app/common/model/user.input.model';
import { UserComponent } from './user.component';

@Injectable({
  providedIn: 'root',
})
export class UserMapperService {
  constructor() {}

  mapComponentToInput(user: UserComponent): UserInput {
    return {
      pseudo: '',
      mail: '',
      nom: '',
      prenom: '',
    };
  }
}

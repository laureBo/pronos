import { Injectable } from '@angular/core';
import { UserInput } from 'src/app/common/model/user.input.model';
import { UserOutput } from 'src/app/common/model/user.output.model';
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
      password: '',
      nom: '',
      prenom: '',
    };
  }
  mapComponentToOutput(user: UserComponent): UserOutput {
    return {
      pseudo: '',
      mail: '',
      password: '',
      nom: '',
      prenom: '',
    };
  }
}

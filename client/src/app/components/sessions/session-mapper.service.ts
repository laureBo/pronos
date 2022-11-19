import { Injectable, Input } from '@angular/core';
import {
  SessionInput,
  SessionLightInput,
} from 'src/app/common/model/session.input.model';
import {
  SessionStatusEnum,
  SessionSummary,
} from './session-summary/session-summary.model';

@Injectable({
  providedIn: 'root',
})
export class SessionMapperService {
  constructor() {}

  mapInputToComponent(input: SessionLightInput): SessionSummary {
    return {
      title: input.nomSession,
      id: input.id,
      date: input.dateCreationSession,
      creator: input.pseudoCreateur,
      status: SessionStatusEnum.INPROGRESS,
    };
  }
}

import { Injectable, Input } from '@angular/core';
import {
  SessionInput,
  SessionLightInput,
} from 'src/app/common/model/session.input.model';
import {
  SessionStatusEnum,
  SessionSummary,
  SessionSummaryComplete,
} from './session-summary/session-summary.model';
import { Match } from '../match/match.models';

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
  mapInputCompleteToComponent(input: SessionInput): SessionSummaryComplete {
    return {
      title: input.nomSession,
      id: input.id,
      date: input.dateCreationSession,
      creator: input.pseudoCreateur,
      matchs: [],
      participants: [],
    };
  }
}

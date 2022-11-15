import { Component, Input, OnInit } from '@angular/core';
import { SessionSummary } from './session-summary.model';

@Component({
  selector: 'app-session-summary',
  templateUrl: './session-summary.component.html',
  styleUrls: ['./session-summary.component.scss'],
})
export class SessionSummaryComponent implements OnInit {
  @Input()
  input: SessionSummary;

  constructor() {}

  ngOnInit(): void {}
}

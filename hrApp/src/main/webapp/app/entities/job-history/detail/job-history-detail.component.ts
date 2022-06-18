import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobHistory } from '../job-history.model';

@Component({
  selector: 'jhi-job-history-detail',
  templateUrl: './job-history-detail.component.html',
})
export class JobHistoryDetailComponent implements OnInit {
  jobHistory: IJobHistory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jobHistory }) => {
      this.jobHistory = jobHistory;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

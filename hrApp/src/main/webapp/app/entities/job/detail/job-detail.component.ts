import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJob } from '../job.model';

@Component({
  selector: 'jhi-job-detail',
  templateUrl: './job-detail.component.html',
})
export class JobDetailComponent implements OnInit {
  job: IJob | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ job }) => {
      this.job = job;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

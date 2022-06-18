import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITask } from '../task.model';

@Component({
  selector: 'jhi-task-detail',
  templateUrl: './task-detail.component.html',
})
export class TaskDetailComponent implements OnInit {
  task: ITask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ task }) => {
      this.task = task;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITask } from '../task.model';
import { TaskService } from '../service/task.service';

@Component({
  templateUrl: './task-delete-dialog.component.html',
})
export class TaskDeleteDialogComponent {
  task?: ITask;

  constructor(protected taskService: TaskService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.taskService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

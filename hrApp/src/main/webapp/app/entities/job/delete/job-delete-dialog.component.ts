import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IJob } from '../job.model';
import { JobService } from '../service/job.service';

@Component({
  templateUrl: './job-delete-dialog.component.html',
})
export class JobDeleteDialogComponent {
  job?: IJob;

  constructor(protected jobService: JobService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.jobService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

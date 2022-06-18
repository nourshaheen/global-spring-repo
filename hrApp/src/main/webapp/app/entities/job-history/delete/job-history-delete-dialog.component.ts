import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IJobHistory } from '../job-history.model';
import { JobHistoryService } from '../service/job-history.service';

@Component({
  templateUrl: './job-history-delete-dialog.component.html',
})
export class JobHistoryDeleteDialogComponent {
  jobHistory?: IJobHistory;

  constructor(protected jobHistoryService: JobHistoryService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.jobHistoryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegion } from '../region.model';
import { RegionService } from '../service/region.service';

@Component({
  templateUrl: './region-delete-dialog.component.html',
})
export class RegionDeleteDialogComponent {
  region?: IRegion;

  constructor(protected regionService: RegionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.regionService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICountry } from '../country.model';
import { CountryService } from '../service/country.service';

@Component({
  templateUrl: './country-delete-dialog.component.html',
})
export class CountryDeleteDialogComponent {
  country?: ICountry;

  constructor(protected countryService: CountryService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.countryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

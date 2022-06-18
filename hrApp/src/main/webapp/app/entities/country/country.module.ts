import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CountryComponent } from './list/country.component';
import { CountryDetailComponent } from './detail/country-detail.component';
import { CountryUpdateComponent } from './update/country-update.component';
import { CountryDeleteDialogComponent } from './delete/country-delete-dialog.component';
import { CountryRoutingModule } from './route/country-routing.module';

@NgModule({
  imports: [SharedModule, CountryRoutingModule],
  declarations: [CountryComponent, CountryDetailComponent, CountryUpdateComponent, CountryDeleteDialogComponent],
  entryComponents: [CountryDeleteDialogComponent],
})
export class CountryModule {}
